package lt.jsdev.flickrapi;

public class FlickrApi {

    // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg

    private String mFlickrServerId;
    private String mFlckrPhotoId;
    private String mFlckrSecret;
    private int mFlickrFarmId;
    private String mFlckrPhotoTitle;

    public int getFlickrFarmId() {
        return mFlickrFarmId;
    }

    public void setFlickrFarmId(int flickrFarmId) {
        mFlickrFarmId = flickrFarmId;
    }

    public String getFlickrServerId() {
        return mFlickrServerId;
    }

    public void setFlickrServerId(String flickrServerId) {
        mFlickrServerId = flickrServerId;
    }

    public String getFlckrPhotoId() {
        return mFlckrPhotoId;
    }

    public void setFlckrPhotoId(String flckrPhotoId) {
        mFlckrPhotoId = flckrPhotoId;
    }

    public String getFlckrSecret() {
        return mFlckrSecret;
    }

    public void setFlckrSecret(String flckrSecret) {
        mFlckrSecret = flckrSecret;
    }

    public String getFlckrPhotoTitle() {
        return mFlckrPhotoTitle;
    }

    public void setFlckrPhotoTitle(String flckrPhotoTitle) {
        mFlckrPhotoTitle = flckrPhotoTitle;
    }

}
