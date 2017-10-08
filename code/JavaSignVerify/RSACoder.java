import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.Cipher;


/**
 * 
 *  ���RSA�����У����� padding ���������ݳ��ȵ�Ҫ�󣺱���1024λ�ģ����ĳ��Ȳ��ܳ���117��
	1024λ=1024bit=128byte��128-11=117��

	˽Կ���ܣ�
	RSA_PKCS1_PADDING RSA_size-11
	RSA_NO_PADDING RSA_size-0
	RSA_X931_PADDING RSA_size-2
	��Կ����
	RSA_PKCS1_PADDING RSA_size-11
	RSA_SSLV23_PADDING RSA_size-11
	RSA_X931_PADDING RSA_size-2
	RSA_NO_PADDING RSA_size-0
	RSA_PKCS1_OAEP_PADDING RSA_size-2 * SHA_DIGEST_LENGTH-2
 */
public class RSACoder extends Coder{
	public static final String KEY_AlGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static final String PUBLICKEY = "RSAPublicKey";
	private static final String PRIVATEKEY = "RSAPrivateKey";
	public static final String MODE = "RSA/ECB/PKCS1Padding";
	//RSAֻ֧��ECB���뱾����ģʽ������ֻ֧��NoPadding��PKCS1Padding
	//��"RSA/ECB/OAEPWITHMD5ANDMGF1PADDING", 
	//��"RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING", 
	//"RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING".
	
	//����˽Կǩ��
	public static String sign(byte[] data,String privateKey) throws Exception{
		long oldTime = System.currentTimeMillis();
		byte[] keyBytes = decryptBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_AlGORITHM);
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		String result = encryBase64(signature.sign());
		long currentTime = System.currentTimeMillis();
		System.out.println("����˽Կǩ�����õ�ʱ�䣺"+(currentTime-oldTime));
		return result;
		
	}
	//����˽Կ��֤ǩ��
	public static Boolean verifySign(byte[] data,String publicKey,String sign) throws Exception{
		long oldTime = System.currentTimeMillis();
		byte[] keyBytes = decryptBase64(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_AlGORITHM);
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);
		Boolean isVerified = signature.verify(decryptBase64(sign));
		long currentTime = System.currentTimeMillis();
		System.out.println("����˽Կ��֤ǩ�����õ�ʱ�䣺"+(currentTime-oldTime));
		return isVerified;
	}
	//����˽Կ������Ϣ��������
	public static String encryptByPrivateKey(byte data[],String priKey) throws Exception{
		long oldTime = System.currentTimeMillis();
		byte[] keyBytes = decryptBase64(priKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_AlGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(MODE);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		String result = encryBase64(cipher.doFinal(data));
		long currentTime = System.currentTimeMillis();
		System.out.println("����˽Կ������Ϣ���õ�ʱ�䣺"+(currentTime-oldTime));
		return result;
	}
	
	//˫�ؼ��ܣ�����������������Ϊ��Կ�ĳ��Ⱥ����ĵĳ���֮����һ�������ơ�
	public static String doubleEncryptByPrivateKey(byte data[],String priKey) throws Exception{
		return encryptByPrivateKey(encryptByPrivateKey(data, priKey).getBytes(),priKey);
	}
	
	//����˽Կ�����Ҵ�������Ϣ
	public static byte[] decryptByPrivateKey(String data,String priKey) throws Exception{
		long oldTime = System.currentTimeMillis();
		byte[] keyBytes = decryptBase64(priKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_AlGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(MODE);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte result[] = cipher.doFinal(decryptBase64(data));
		long currentTime = System.currentTimeMillis();
		System.out.println("����˽Կ������Ϣ���õ�ʱ�䣺"+(currentTime-oldTime));
		return result;
	}
	//���ù�Կ����
	public static String encryptByPublicKey(byte data[],String pubKey) throws Exception{
		long oldTime = System.currentTimeMillis();
		byte[] keyBytes = decryptBase64(pubKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_AlGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance(MODE);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		String result = encryBase64(cipher.doFinal(data));
		long currentTime = System.currentTimeMillis();
		System.out.println("���ù�Կ������Ϣ���õ�ʱ�䣺"+(currentTime-oldTime));
		return result;
	}
	//���ù�Կ���ܼ״�������Ϣ
	public static byte[] decryptByPublicKey(String data,String pubKey) throws Exception{
		long oldTime = System.currentTimeMillis();
		byte[] keyBytes = decryptBase64(pubKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_AlGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance(MODE);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte result[] = cipher.doFinal(decryptBase64(data));
		long currentTime = System.currentTimeMillis();
		System.out.println("���ù�Կ������Ϣ���õ�ʱ�䣺"+(currentTime-oldTime));
		return result;
	}
	//��ȡ��Կ
	public static String getPrivateKey(Map<String ,Object> keyMap){
		RSAPrivateKey key = (RSAPrivateKey)keyMap.get(PRIVATEKEY);
		return encryBase64(key.getEncoded()); 
	}
	//���˽Կ
	public static String getPublicKey(Map<String,Object> keyMap){
		RSAPublicKey key = (RSAPublicKey)keyMap.get(PUBLICKEY);
		return encryBase64(key.getEncoded());
	}
	//��ù�Կ��˽Կ
	public static Map<String,Object> initKey(int keyLen) throws NoSuchAlgorithmException{
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(KEY_AlGORITHM);
		keyGenerator.initialize(keyLen);
		KeyPair keyPair = keyGenerator.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(PRIVATEKEY, privateKey);
		map.put(PUBLICKEY, publicKey);
		return map;
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("��������Կ���ȣ�");
		Scanner scanner = new Scanner(System.in);
		
		int keyLen = scanner.nextInt();
		System.out.println("�ס�������������������������������������������������������������������������������>��,��ʱ��Կ����Ϊ��"+keyLen+"\n");
		testA(keyLen);
		System.out.println("\n"+"�ҡ�������������������������������������������������������������������������������>��,��ʱ��Կ����Ϊ��"+keyLen+"\n");
		testB(keyLen);

	}
	public static void testA(int keyLen) throws Exception{
		//�������Լ���˽Կ��������
		String plainText = "h!";
		System.out.println("����ǰ�����ǣ�"+plainText);
		//�׻��˽Կ
		long oldTime = System.currentTimeMillis();
		Map<String,Object> map = initKey(keyLen);
		String privateKey = getPrivateKey(map);
		long currentTime = System.currentTimeMillis();
		System.out.println("���˽Կ��ʱ�䣺"+(currentTime-oldTime));
		
		String cipherText = encryptByPrivateKey(plainText.getBytes(), privateKey);
		System.out.println("����˽Կ���ܺ�������ǣ�"+cipherText);
		
		//String cipherText1 = doubleEncryptByPrivateKey(plainText.getBytes(), privateKey);
		//System.out.println("����˽Կ���μ��ܺ�������ǣ�"+cipherText1);
		//�����Լ���˽Կǩ��
		String sign = sign(plainText.getBytes(), privateKey);
		System.out.println("����˽Կǩ����Ϊ:"+sign);
	
		//�һ�ü׵����ĺ��ü׵Ĺ�Կ����
		String publicKey = getPublicKey(map);
		if(verifySign(plainText.getBytes(), publicKey, sign)){
			plainText = new String(decryptByPublicKey(cipherText, publicKey));
			System.out.println("���ü׵Ĺ�Կ���ܺ������ǣ�"+plainText);
		}
	}
	public static void testB(int keyLen) throws Exception{
		long oldTime = System.currentTimeMillis();
		Map<String,Object> map = initKey(keyLen);
		//�����ü׵Ĺ�Կ���ܺ��͸���
		String plainText = "�Ҵ��������ݣ�ahahahah!";
		System.out.println("����ǰ�����ǣ�"+plainText);
		String publicKey = getPublicKey(map);
		long currentTime = System.currentTimeMillis();
		System.out.println("��ù�Կ��ʱ�䣺"+(currentTime-oldTime));
		
		String cipherText = encryptByPublicKey(plainText.getBytes(), publicKey);
		System.out.println("���ü׵Ĺ�Կ���ܺ������ǣ�"+cipherText);
		//�׻��˽Կ���������Լ���˽Կ������
		String privateKey = getPrivateKey(map);
		plainText = new String(decryptByPrivateKey(cipherText, privateKey));
		System.out.println("�����Լ���˽Կ���ܺ�������ǣ�"+plainText);
	
}
		
}
