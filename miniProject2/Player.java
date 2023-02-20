import java.util.ArrayList;

public class Player
{
  private static String name;
  private static int level;
  private static ArrayList inventory;
  private static int animalsHealed;

  public Player() // player constructor
  {
  }

  public Player(String name, int level, ArrayList inventory, int animalsHealed)
  {
    this.name=name;
    this.level=level;
    this.inventory=inventory;
    this.animalsHealed=animalsHealed;
  }

  public static void setName(String newName) // set name
  {
    name = newName;
  }

  public static void setLevel(int newLevel) // set level
  {
    level = newLevel;
  }

  public void setInventory(ArrayList newInventory) // set inventory
  {
    this.inventory = newInventory;
  }

  public static void setAnimalsHealed(int newAnimalsHealed) // set number of animals healed
  {
    animalsHealed = newAnimalsHealed;
  }

  public static String getName() // returns name
  {
    return name;
  }

  public static int getLevel() // returns level
  {
    return level;
  }

  public static ArrayList getInventory() // returns inventory
  {
    return inventory;
  }

  public static int getAnimalsHealed() // returns number
  {
    return animalsHealed;
  }
}
