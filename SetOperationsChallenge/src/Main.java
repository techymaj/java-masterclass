import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("--- All Tasks ---", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("ann");
        sortAndPrint("--- Ann's Tasks ---", annsTasks, sortByPriority);

        System.out.println("Get Union");
        var getAnns = TaskData.getTasks("ann");
        var getBobs = TaskData.getTasks("bob");
        var getCarols = TaskData.getTasks("carol");
        var allTasks = TaskData.getTasks("all");
        var listOfSets = List.of(getBobs, getAnns, getCarols, allTasks);

        // full task list
        var union = getUnion(listOfSets);
        sortAndPrint("UNION", union);


        var intersection = getIntersect(getBobs, getAnns);
        sortAndPrint("Intersection of bob and ann's tasks", intersection);

        var difference = getDifference(getBobs, getAnns);
        sortAndPrint("Difference of bob and ann's tasks", difference);

        var atLeastOne = getIntersect( union, getAnns);
        sortAndPrint("Assigned to at least one team member", atLeastOne);

        var listOfAssigned = List.of(getAnns,getBobs,getCarols);
        var assigned = getUnion(listOfAssigned);

        var unassigned = getDifference(union, assigned);
        sortAndPrint("Un assigned", unassigned);

    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection,
                                     Comparator<Task> sorter) {
        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    public static Set<Task> getUnion(List<Set<Task>> taskList) {
        var union = new HashSet<Task>();
        for (var task: taskList) {
            union.addAll(task);
        }
        return union;
    }

    public static Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        var intersect = new HashSet<>(set1);
        intersect.retainAll(set2);
        return intersect;
    }

    public static Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        var difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }
}
