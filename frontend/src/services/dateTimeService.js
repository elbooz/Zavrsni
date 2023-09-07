import axios from "axios";
import { api_url } from "../App";

class dataService {
    async oneDateTime(datetime) {
        return await axios.get(api_url + "getByDatum?DatumVrijeme=" + datetime);
    }
}

export default new dataService();