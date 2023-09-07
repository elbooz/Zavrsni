import axios from "axios";
import { api_url } from "../App";

class JsonService {
    oneJson(Json) {
        return axios({
            method: 'post',
            url: api_url + 'getByDatumAndCoordinates',
            headers: {}, 
            data: Json
          })
    }
}

export default new JsonService();