package at.ac.tuwien.controller.ui;

public abstract class AbstractUiController {
    protected abstract String getViewDir();

    protected String createViewPath(String viewName) {
        return getViewDir() + "/" + viewName;
    }
}
