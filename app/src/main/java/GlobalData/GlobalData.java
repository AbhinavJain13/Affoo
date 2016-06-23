package GlobalData;

/**
 * Created by Ramakant on 6/23/2016.
 */
public class GlobalData {

    private static String userId;
    private GlobalData(){}

    private static class GlobalInstance{
        private static final GlobalData INSTANCE = new GlobalData();
    }

    public static GlobalData getGlobalInstance(){
        return GlobalInstance.INSTANCE;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        GlobalData.userId = userId;
    }

    public enum orderStatus{
        CURRENT,
        PROCESSED,
        CANCELED,
        COMPLETED,
        CRITICAL
    }

}
