public class Main {
  public static void main(String[] args) {
    LinkedList<String> groceries = new LinkedList<>();
    LinkedList<String> courses = new LinkedList<>();

    groceries.add("Milk");
    groceries.add("Eggs");

   /* System.out.println("\n" + groceries);

    groceries.add(0, "Muffins");
    groceries.add(0, "Bananas");

    System.out.println("\n" + groceries);

    groceries.remove("Milk");

    System.out.println("\n" + groceries);

    groceries.remove(0);

    System.out.println("\n" + groceries);

    groceries.set(1, "Guacamole");

    System.out.println("\n" + groceries);

    */


    courses.add("CS 111");
    courses.add("CS 112");
    courses.add("CS 113");
    courses.add("CS 220");
    courses.add("CS 226");
    courses.add("CS 292");


    //Situation: while looping through a list, try to add/remove an element
    //breaks code (runtime error) ConcurrentModificationException

    //Use the newly created ListIterator data structure:

    LinkedList<String>.ListIterator li = courses.new ListIterator();

    System.out.println("\nPrinting Forwards");
    while (li.hasNext())
      System.out.println(li.next());

    System.out.println("\nPrinting Backwards");
    while (li.hasPrevious())
      System.out.println(li.previous());

  }


}