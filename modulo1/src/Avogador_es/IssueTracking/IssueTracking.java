package Avogador_es.IssueTracking;

abstract class Issue {
    private final int ID;

    protected Issue(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}

class BugReport extends Issue {

    public BugReport(int ID) {
        super(ID);
    }
}

class FeatureRequest extends Issue {

    public FeatureRequest(int ID) {
        super(ID);
    }
}

class MaintenanceTask extends Issue {

    public MaintenanceTask(int ID) {
        super(ID);
    }
}

class TrackingSystem {

    public static int numberOfBugReports(Issue[] issues) {
        int count = 0;
        for (Issue issue : issues) {
            if (issue instanceof BugReport)
                count++;
        }
        return count;
    }

    public static int numberOfFeatureRequests(Issue[] issues) {
        int count = 0;
        for (Issue issue : issues) {
            if (issue instanceof FeatureRequest)
                count++;
        }
        return count;
    }

    public static int numberOfMaintenanceTasks(Issue[] issues) {
        int count = 0;
        for (Issue issue : issues) {
            if (issue instanceof MaintenanceTask)
                count++;
        }
        return count;
    }

    public static int mostRecentIssue(Issue[] issues) {
        if (issues.length == 0)
            return 0;

        int maxID = issues[0].getID();
        for (Issue issue : issues) {
            if (issue.getID() > maxID)
                maxID = issue.getID();
        }
        return maxID;
    }
}