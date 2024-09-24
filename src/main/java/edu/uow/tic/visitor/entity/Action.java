package edu.uow.tic.visitor.entity;

public enum Action {
    VISITOR_INFORMATION_CREATE("VISITOR_INFORMATION_CREATE"),
    VISITOR_INFORMATION_UPDATE("VISITOR_INFORMATION_UPDATE"),
    VISITOR_INFORMATION_DELETE("VISITOR_INFORMATION_DELETE"),
    VISITOR_INFORMATION_GET_BY_ID("VISITOR_INFORMATION_GET_BY_ID"),
    VISITOR_INFORMATION_GET_ALL("VISITOR_INFORMATION_GET_ALL");

    private final String actionName;

    private Action(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    @Override
    public String toString() {
        return actionName;
    }
}
