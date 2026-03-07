package ar.edu.dsi.gpstracker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gps.api")
public class GpsApiProperties {

    private Keys keys = new Keys();

    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        this.keys = keys;
    }

    public static class Keys {
        private String xtr4500l;
        private String navtrackQx7a;
        private String geopulseMtr900;

        public String getXtr4500l() {
            return xtr4500l;
        }

        public void setXtr4500l(String xtr4500l) {
            this.xtr4500l = xtr4500l;
        }

        public String getNavtrackQx7a() {
            return navtrackQx7a;
        }

        public void setNavtrackQx7a(String navtrackQx7a) {
            this.navtrackQx7a = navtrackQx7a;
        }

        public String getGeopulseMtr900() {
            return geopulseMtr900;
        }

        public void setGeopulseMtr900(String geopulseMtr900) {
            this.geopulseMtr900 = geopulseMtr900;
        }
    }
}
