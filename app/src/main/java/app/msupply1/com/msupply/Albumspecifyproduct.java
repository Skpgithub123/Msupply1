package app.msupply1.com.msupply;

public class Albumspecifyproduct {
    private String name;
    private String productid;
    private int thumbnail;

    public Albumspecifyproduct() {
    }

    public Albumspecifyproduct(String name, String productid, int thumbnail) {
        this.name = name;
        this.productid = productid;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductID() {
        return productid;
    }

    public void setProductID(String productid) {
        this.productid = productid;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
