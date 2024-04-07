import { IUserLoginInfo } from "../intefaces/IUserLoginInfo";

const baseURL = "http://localhost:8080/users";

export const UsersAPI = {
    login: async function (username: string, password: string) {
        const response = await fetch(baseURL + "/login", {
            headers: {
                "Accept": "application/json",
                "Content-type": "application/json"
            },
            method: "POST",
            body: JSON.stringify({ "username": username, "password": password })
        })

        return response.json() as Promise<IUserLoginInfo>;
    },

    register: async function(username: string, password: string) {
        const response = await fetch(baseURL + "/register", {
            headers: {
                "Accept": "application/json",
                "Content-type": "application/json"
            },
            method: "POST",
            body: JSON.stringify({ "username": username, "password": password })
        })

        return response.json() as Promise<IUserLoginInfo>;
    }
}