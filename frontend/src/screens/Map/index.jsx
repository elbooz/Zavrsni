import React, { useEffect } from 'react';
import {MapContainer, TileLayer, Marker, Popup, useMap, useMapEvents} from "react-leaflet";
import L from 'leaflet';
import fire from '../../assets/images/fire-icon.png';
import cloud from '../../assets/images/cloud-icon.png';
import polar from '../../assets/images/polar-icon.png';
import bioluminescence from '../../assets/images/bioluminescence-icon.png';
import noData from '../../assets/images/noData.png';
import { Navigate } from 'react-router-dom';
import { useState } from "react";
import dateTimeService from '../../services/dateTimeService';
import Button from '../../components/Button';
import { useNavigate } from 'react-router-dom';
import polar5 from '../../assets/images/polar5.png';
import polar4 from '../../assets/images/polar4.png';
import polar3 from '../../assets/images/polar3.png';
import polar2 from '../../assets/images/polar2.png';
import polar1 from '../../assets/images/polar1.png';
import prikaz from '../../assets/images/prikaz.png';

import "./Map.css"


const Map = () => {

  const searchParams = new URLSearchParams(document.location.search)
  const dateTime = searchParams.get('dateTime');
  const type = searchParams.get('type');
  const navigate = useNavigate();
  let data = {};
  const [pojave, setPojave] = useState([]);
  const [timestamp, setTimestamp] = useState([]);

  const fetchData = async () => {
    data = await (await dateTimeService.oneDateTime(dateTime)).data;
    setTimestamp(data.timestamp);
    if (type == 3) {
      setPojave(data.polarna);
    }
  }
 

  useEffect(() => {
    console.log(type, dateTime);
    fetchData();
  },[]);

  const handleClick3 = () => {
    navigate('/map?dateTime=' + dateTime + '&type=3');
    window.location.reload(true);
  }

  const handleClick5 = () => {
    navigate('/');
  }
  
  function createIcon(opacity, longitude) {
    if (longitude < 70 && longitude > -70) {
    if(opacity == 0.1) {
      return L.icon({
        iconUrl: polar1,
        iconRetinaUrl: polar1,
        iconSize: [5, 5],
       // opacity: opacity,
      });
    } else if (opacity == 0.2) {
    return L.icon({
      iconUrl: polar2,
      iconRetinaUrl: polar2,
      iconSize: [5, 5],
     // opacity: opacity,
    });
  } else if (opacity == 0.3) {
    return L.icon({
      iconUrl: polar3,
      iconRetinaUrl: polar3,
      iconSize: [5, 5],
     // opacity: opacity,
    });
  } else if (opacity == 0.4) {
    return L.icon({
      iconUrl: polar4,
      iconRetinaUrl: polar4,
      iconSize: [5, 5],
      //opacity: opacity,
    });
  } else if (opacity >= 0.5) {
    return L.icon({
      iconUrl: polar5,
      iconRetinaUrl: polar5,
      iconSize: [5, 5],
      //opacity: opacity,
    });
  }
} else {
  if(opacity == 0.1) {
    return L.icon({
      iconUrl: polar1,
      iconRetinaUrl: polar1,
      iconSize: [5, 8],
     // opacity: opacity,
    });
  } else if (opacity == 0.2) {
  return L.icon({
    iconUrl: polar2,
    iconRetinaUrl: polar2,
    iconSize: [5, 8],
   // opacity: opacity,
  });
} else if (opacity == 0.3) {
  return L.icon({
    iconUrl: polar3,
    iconRetinaUrl: polar3,
    iconSize: [5, 8],
   // opacity: opacity,
  });
} else if (opacity == 0.4) {
  return L.icon({
    iconUrl: polar4,
    iconRetinaUrl: polar4,
    iconSize: [5, 8],
    //opacity: opacity,
  });
} else if (opacity >= 0.5) {
  return L.icon({
    iconUrl: polar5,
    iconRetinaUrl: polar5,
    iconSize: [5, 8],
    //opacity: opacity,
  });
}

}
  }

  function createIcon2(opacity, longitude) {
    if (longitude < 62 && longitude > -62 ) {
    if(opacity == 0.1) {
      return L.icon({
        iconUrl: polar1,
        iconRetinaUrl: polar1,
        iconSize: [5, 6],
       // opacity: opacity,
      });
    } else if (opacity == 0.2) {
    return L.icon({
      iconUrl: polar2,
      iconRetinaUrl: polar2,
      iconSize: [5, 6],
     // opacity: opacity,
    });
  } else if (opacity == 0.3) {
    return L.icon({
      iconUrl: polar3,
      iconRetinaUrl: polar3,
      iconSize: [5, 6],
     // opacity: opacity,
    });
  } else if (opacity == 0.4) {
    return L.icon({
      iconUrl: polar4,
      iconRetinaUrl: polar4,
      iconSize: [5, 6],
      //opacity: opacity,
    });
  } else if (opacity >= 0.5) {
    return L.icon({
      iconUrl: polar5,
      iconRetinaUrl: polar5,
      iconSize: [5, 6],
      //opacity: opacity,
    });
  } else if ( longitude > 72 || longitude < -72) {
    if(opacity == 0.1) {
      return L.icon({
        iconUrl: polar1,
        iconRetinaUrl: polar1,
        iconSize: [5, 9],
       // opacity: opacity,
      });
    } else if (opacity == 0.2) {
    return L.icon({
      iconUrl: polar2,
      iconRetinaUrl: polar2,
      iconSize: [5, 9],
     // opacity: opacity,
    });
  } else if (opacity == 0.3) {
    return L.icon({
      iconUrl: polar3,
      iconRetinaUrl: polar3,
      iconSize: [5, 9],
     // opacity: opacity,
    });
  } else if (opacity == 0.4) {
    return L.icon({
      iconUrl: polar4,
      iconRetinaUrl: polar4,
      iconSize: [5, 9],
      //opacity: opacity,
    });
  } else if (opacity >= 0.5) {
    return L.icon({
      iconUrl: polar5,
      iconRetinaUrl: polar5,
      iconSize: [5, 9],
      //opacity: opacity,
    });
  }
} else {
  if(opacity == 0.1) {
    return L.icon({
      iconUrl: polar1,
      iconRetinaUrl: polar1,
      iconSize: [5, 7.5],
     // opacity: opacity,
    });
  } else if (opacity == 0.2) {
  return L.icon({
    iconUrl: polar2,
    iconRetinaUrl: polar2,
    iconSize: [5, 7.5],
   // opacity: opacity,
  });
} else if (opacity == 0.3) {
  return L.icon({
    iconUrl: polar3,
    iconRetinaUrl: polar3,
    iconSize: [5, 7.5],
   // opacity: opacity,
  });
} else if (opacity == 0.4) {
  return L.icon({
    iconUrl: polar4,
    iconRetinaUrl: polar4,
    iconSize: [5, 7.5],
    //opacity: opacity,
  });
} else if (opacity >= 0.5) {
  return L.icon({
    iconUrl: polar5,
    iconRetinaUrl: polar5,
    iconSize: [5, 7.5],
    //opacity: opacity,
  });
}
} 
}
  
  }
  
  
  
  return (
    <div className="App">
      <div className='form-container'>
      <img src={prikaz}  alt="prikaz"  style={{ position: 'absolute', left: 0, top: 0, width: 300, height: 90, marginLeft: 30, marginTop: 30 }}/>
        <div className="filter" onClick={() => handleClick3()}>
            <Button color="#4080bf" text="Polarna svjetlost"></Button>
        </div>
        <div className="filter" onClick={() => handleClick5()}>
            <Button color="#4080bf" text="Povratak"></Button>
        </div> 
     
          {timestamp != null ? (
        <h1 style={{ color: 'white', textAlign: 'right', position: 'absolute', right: 30 }}>
          {timestamp}
        </h1>
      ) : (
        <h1 style={{ color: 'white', textAlign: 'right', position: 'absolute', right: 30 }}>
         NE POSTOJE PODACI
        </h1>
      )}
      </div>

      <div className="map" id="map">

         <MapContainer
            style={{height: "90vh"}}
            center={[20, 0]}
            zoom={2}
            scrollWheelZoom={true}>
            <TileLayer
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
            { type == 3 ?
              (pojave ? pojave.map(pojava => (<Marker position={[pojava.primaryKey.latitude, pojava.primaryKey.longitude - 180]} icon={ createIcon(pojava.prisutnost / 10, pojava.primaryKey.latitude) } ></Marker>)) : null ) : null
            }
           
          </MapContainer>
        </div>
    </div>
  )
}

export default Map
