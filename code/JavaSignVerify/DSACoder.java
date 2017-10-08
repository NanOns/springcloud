import sun.misc.BASE64Encoder;
import java.io.IOException;
import sun.misc.BASE64Decoder;
 
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


public class DSACoder extends Coder{
	private static final String ALGORITHM = "DSA";
	private static final int KEY_SIZE = 1024;
	private static final String DEFAULT_SEED = "0F22507A10ALJFLAJFeLAKJDJDFLAKJDFJ";
	private static final String PUBLIC_KEY = "DSAPublicKey";
	private static final String PRIVATE_KEY = "DSAPrivateKey";
	/** 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = initKey(DEFAULT_SEED);
		//����˽Կǩ��
		String privateKey = getPrivateKey(map);
		String data ="��Ϣ��ȫ�����";
		System.out.println("ǩ��ǰ����Ϣ�ǣ�"+data);
		System.out.println("˽Կ�ǣ�"+privateKey);
		String sign = sign(data.getBytes(), privateKey);
		System.out.println("ǩ������ı��ǣ�"+sign);
		
		//���ù�Կ��֤ǩ��
		String publicKey = getPublicKey(map);
		System.out.println("��Կ�ǣ�"+publicKey);
		boolean isVerify = verify(data.getBytes(), publicKey, sign);
		if(isVerify){
			System.out.println("ͨ����֤��ǩ����Ч��");
		}
	}
	//��ʼ����Կ��,������������������Լ�����Կ��
	public static Map<String,Object> initKey(String seed) throws NoSuchAlgorithmException{
		KeyPairGenerator kengen = KeyPairGenerator.getInstance(ALGORITHM);
		SecureRandom random = new SecureRandom();
		random.setSeed(seed.getBytes());
		kengen.initialize(KEY_SIZE, random);
		
		KeyPair keyPair = kengen.genKeyPair();
		DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();
		DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
		return map;
		
	}
	
	//����Լ��Ĺ�Կ
	public static String getPublicKey(Map<String,Object> map){
		Key key = (Key) map.get(PUBLIC_KEY);
		return encryBase64(key.getEncoded());
	}
	
	//����Լ���˽Կ
	public static String getPrivateKey(Map<String,Object> map){
		Key key = (Key) map.get(PRIVATE_KEY);
		return encryBase64(key.getEncoded());
	}
	//ǩ��
	public static String sign(byte[] data, String privateKey) throws Exception{
		byte[] privateKeyBytes = decryptBase64(privateKey);
		//����PKCS8EncodedKeySpec����ԭ˽ԿPrivateKey
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		
		Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
		signature.initSign(priKey);
		signature.update(data);
		return encryBase64(signature.sign()); 
	}
	//��֤ǩ��
	public static boolean verify(byte[] data ,String publicKey,String sign)throws Exception{
		byte[] publicKeyBytes = decryptBase64(publicKey);
		//����X509EncodedKeySpec����ԭ��ԿPublicKey
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		
		Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
		signature.initVerify(pubKey);
		signature.update(data);
		return signature.verify(decryptBase64(sign));
	}
}
