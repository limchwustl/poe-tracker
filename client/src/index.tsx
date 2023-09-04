import React, { useState, useRef, useEffect } from "react";
import ItemQuery from "./model/ItemQuery";
import axios from "axios"
import {leagueEnum, typeEnum, itemEnum} from './model/Enums'
const index = () => {
  const [itemQuery, setItemQuery] = useState(new ItemQuery("", "", ""));
  const [queryHistory, setqueryHistory] = useState([])
  const leagueRef = useRef("")
  const typeRef = useRef("")
  const itemRef = useRef("")
  
  useEffect(()=>{

    axios({
      url: "http://localhost:18080/api/items",
      method: "GET",
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
      },

 
    
  })

      // Handle the response from backend here
      .then((res) => { 

        console.log(res.data.data)
        setqueryHistory(queryHistory=>res.data.data)

      })

     
      .catch((err) => { });
  }, [])

  function handleSubmit (event){
    event.preventDefault()
    axios({
      url: "http://localhost:18080/api/items",
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      data: {
        league: leagueRef.current.value,
        type: typeRef.current.value,
        item_name: itemRef.current.value
      }
    })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {});
  };
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
        League
        <select id="league" name="league" ref={leagueRef}>
            {Object.entries(leagueEnum).map((i,league)=>{
                return <option>{i[1]}</option>
            })}

  
        </select>
        </label>
        <label>
        Type
        <select id="type" name="type" ref={typeRef}>
            {Object.entries(typeEnum).map((i,type)=>{
                return <option>{i[1]}</option>
            })}

  
        </select>
        </label>

        <label>
        Type
        <select id="item_name" name="item_name" ref={itemRef}>
            {Object.entries(itemEnum).map((i)=>{
                return <option>{i[1]}</option>
            })}

  
        </select>
        </label>
        <button>search</button>
      </form>

      {
        queryHistory !== [] ? queryHistory.map((i)=>{
            console.log(i)
            return  (

                <React.Fragment key={i.item_name}>
                <p>{i.league + " " + i.type + " " + i.item_name} </p> 
            <img src="https://web.poecdn.com/gen/image/WzI1LDE0LHsiZiI6IjJESXRlbXMvQ3VycmVuY3kvQ3VycmVuY3lEdXBsaWNhdGUiLCJ3IjoxLCJoIjoxLCJzY2FsZSI6MX1d/7111e35254/CurrencyDuplicate.png" alt= "logo"/> 
        
                </React.Fragment>
            )
        }) : <></>
      }
    </div>
  );
};

export default index;
