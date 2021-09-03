package sunwul.learning.springaop.entity;

/*****
 * @author sunwul
 * @date 2021/9/2 9:27
 * @description
 */
public class Test {

    private Integer TestId;
    private String TestName;
    private String TestAddress;
    private boolean isLock;
    private Float TestHeight;

    public Test() {
    }

    public Test(Integer testId, String testName, String testAddress, boolean isLock, Float testHeight) {
        TestId = testId;
        TestName = testName;
        TestAddress = testAddress;
        this.isLock = isLock;
        TestHeight = testHeight;
    }

    @Override
    public String toString() {
        return "Test{" +
                "TestId=" + TestId +
                ", TestName='" + TestName + '\'' +
                ", TestAddress='" + TestAddress + '\'' +
                ", isLock=" + isLock +
                ", TestHeight=" + TestHeight +
                '}';
    }

    public Integer getTestId() {
        return TestId;
    }

    public void setTestId(Integer testId) {
        TestId = testId;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getTestAddress() {
        return TestAddress;
    }

    public void setTestAddress(String testAddress) {
        TestAddress = testAddress;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public Float getTestHeight() {
        return TestHeight;
    }

    public void setTestHeight(Float testHeight) {
        TestHeight = testHeight;
    }
}
