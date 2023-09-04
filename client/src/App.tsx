import { useState , useEffect} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import About from './about'
import Index from './index'
import axios from "axios"


function App() {

 
  return (
    <>
    

    <Router>
    <nav>
      <div style={{"display":"flex", "alignItems":"center"}}>
      <h2>EXILE EXCHANGE</h2>
    <p style={{color: "white", textDecoration: 'none'}} >- POE MARKETPLACE REALTIME TRACKER - </p>

        </div>
   
      <div style ={{"marginRight": "1rem"}}>
      <Link  style={{color: "white",textDecoration: 'none'}} to ="/"> HOME </Link> 
      <Link  style={{color: "white", textDecoration: 'none'}} to ="about">ABOUT </Link>
        </div>
      

      </nav>
      <Routes>
        <Route path = "/about" element = {<About/>}></Route>
        <Route path = "/" element = {<Index/>}></Route>

        </Routes>
      </Router>
    </>
  )
}

export default App
