package qingyun.ele;

public interface TokenUtils {
	String getToken(MCAUserDetails userDetails);

	String getToken(MCAUserDetails userDetails, Long expiration);

	boolean validate(String token);

	MCAUserDetails getUserFromToken(String token);
}