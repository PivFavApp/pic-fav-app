package newagency.picfav.view.main.presenter.model;

/**
 * Created by oroshka on 1/30/18.
 */

public class ImageItemModel {

    private String url;

    private boolean isSelected;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
