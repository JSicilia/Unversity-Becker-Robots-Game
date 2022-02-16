/*
 * Class to automatically run all the tests created
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */

public class Tester
{
  public static void main(String[] args)
  {
    TestManager test = new TestManager();
    
    test.testPlayerCommands();
    
    test.testEnemyCommands();
    
    test.testRoomStorage();
    
    test.testIcons();
  }
}