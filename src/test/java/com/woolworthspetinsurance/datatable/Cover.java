package com.woolworthspetinsurance.datatable;

public class Cover  {

	private String packagetitle;
    private String subtitle;
    private String price;
    private String period;

    public Cover (String packagetitle, String subtitle, String price, String period) {
        this.packagetitle = packagetitle;
        this.subtitle = subtitle;
        this.price = price;
        this.period = period;
    }

    public String getTitle() {
        return packagetitle;
    }
    
    public String getSubTitle() {
        return subtitle;
    }

    public String getPrice() {
        return price;
    }

    public String getPeriod() {
        return period; 
    }
}
