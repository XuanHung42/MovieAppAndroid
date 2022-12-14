package groups.project.movieappwithfirebase.model;

public class TopModel {
    String Tcast;
    String Tcover;
    String Tdesc;
    String Tlink;
    String Ttitle;
    String Ttlink;
    String Tthumb;

    public TopModel() {
    }

    public TopModel(String tcast, String tcover, String tdesc, String tlink, String ttitle, String ttlink, String tthumb) {
        Tcast = tcast;
        Tcover = tcover;
        Tdesc = tdesc;
        Tlink = tlink;
        Ttitle = ttitle;
        Ttlink = ttlink;
        Tthumb = tthumb;
    }

    public String getTcast() {
        return Tcast;
    }

    public void setTcast(String tcast) {
        Tcast = tcast;
    }

    public String getTcover() {
        return Tcover;
    }

    public void setTcover(String tcover) {
        Tcover = tcover;
    }

    public String getTdesc() {
        return Tdesc;
    }

    public void setTdesc(String tdesc) {
        Tdesc = tdesc;
    }

    public String getTlink() {
        return Tlink;
    }

    public void setTlink(String tlink) {
        Tlink = tlink;
    }

    public String getTtitle() {
        return Ttitle;
    }

    public void setTtitle(String ttitle) {
        Ttitle = ttitle;
    }

    public String getTtlink() {
        return Ttlink;
    }

    public void setTtlink(String ttlink) {
        Ttlink = ttlink;
    }

    public String getTthumb() {
        return Tthumb;
    }

    public void setTthumb(String tthumb) {
        Tthumb = tthumb;
    }
}
