export const Utils = {
    setupJwtInfo: function(jwtToken: string) {
        document.cookie = "jwtToken=\"" + jwtToken + "\";SameSite=None; Secure";
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