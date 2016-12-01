package languge;

/**
 * Created by noodles on 16/9/15 下午11:17.
 */
public class TryResourceTest {

    public static void main(String[] args) {
        try (final Resource resource = new Resource()){
            resource.method();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Resource implements AutoCloseable{

    public void method(){
        throw new NullPointerException("");
    }

    @Override
    public void close() throws Exception {
        System.out.println("I released...");
    }
}