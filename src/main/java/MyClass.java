import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {
	private static final Logger logger = LoggerFactory.getLogger(MyClass.class);

	public static void main(String[] args) {
		// Log statements using SLF4J
		logger.trace("This is a TRACE message");
		logger.debug("This is a DEBUG message");
		logger.info("This is an INFO message");
		method1();
		logger.warn("This is a WARN message");
		
	}

	private static void method1() {
		// TODO Auto-generated method stub
		try {
			throw new NullPointerException("this is null pointer exception");
		} catch (Exception e) {
			logger.error("This is an ERROR message", e);
		}
	}
}