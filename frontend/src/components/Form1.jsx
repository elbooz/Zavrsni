import React, { useState } from "react";
import Button from './Button';
import { useNavigate } from 'react-router-dom';
import './Form.css'

const Form1 = () => {
  const [dateTime, setDateTime] = useState("");

  const navigate = useNavigate();

  const handleClick = () => {
      if (dateTime == "") {
        alert("Odaberite datum i vrijeme!!!");
      } else {
        navigate('/map?dateTime=' + dateTime + '&type=1');
      }
  }

  return (
    <div className='form'>
      <div className="form-content">
        <p>Učitaj traženi vremenski trenutak</p>
        <input type="datetime-local" id='datetime' onChange={(e) => {setDateTime(e.target.value)}}/>
        <div onClick={() => handleClick()}>
            <Button color="#6699cc" text="Prihvati"></Button>
        </div>
        </div>
    </div>

  )
}

export default Form1
