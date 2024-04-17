export const Utils = {
    setupJwtInfo: function(jwtToken: string) {
        let now = new Date();
        let time = now.getTime();
        let expireTime = time + 1000*36000;
        now.setTime(expireTime);

        document.cookie = "jwtToken=\"" + jwtToken + "\";SameSite=None; Secure; expires=" + now.toUTCString()+";";
    },
    getJwtInfo: function(): string {
        let cookieInfos: string[] = document.cookie.split(';');
        for (const i in cookieInfos) {
            if(cookieInfos[i].includes("jwtToken")) {
                return cookieInfos[i].split("=")[1].replaceAll('"', "");
            }
        }

        return "";
    }

}