import React, { useState } from "react";
import JsonService from "../services/JsonService";
import Button from './Button';

const Form2 = () => {
    
    const [error, setError] = useState(null);
    const [data, setData] = useState({});
    const [Json, setJson] = useState();
    
    const readFileOnUpload = (uploadedFile) =>{
        const fileReader = new FileReader();
        fileReader.onloadend = ()=>{
           try{
              setJson(JSON.parse(fileReader.result));
              setError(null)
           }catch(e){
              setError("**Not valid JSON file!**");
           }
        }
        if( uploadedFile!== undefined)
           fileReader.readAsText(uploadedFile);
    }

    const handleClick = () => {
        alert("Downloading file");
        const FileDownload = require('js-file-download');
        JsonService.oneJson(Json).then((response) => {
            FileDownload(response.data, 'PrirodnePojave.csv');
        }).catch((res) => {
        setError(res.response.data);
        });
    }
    return (
    <div className='form'>
        <p>Učitaj traženu JSON datoteku</p>
        <input type="file" onChange={(e)=>readFileOnUpload(e.target.files[0])} />
        <div onClick={() => handleClick()}>
              <Button color="white" text="Prihvati"></Button>
        </div>
    </div>
    )

}
    
export default Form2