package rt.tea.com.fireblog;

/**
 * Created by acer on 12/28/2016.
 */

public class Blog {

    private String image;
    private String title;
    private String description;

    public Blog(){


    }

    public Blog(String image,String title, String describtion){
          this.image= image;
        this.title = title;
        this.description = describtion;


    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
