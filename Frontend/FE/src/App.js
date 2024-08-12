import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import Header from './component/Headercp';
import Footer from './component/Footer';
import Main from './Layouts/Main';

import {gapi} from 'gapi-script' ;

const clientId = "349196289553-cbok7vjqcr257sp3qp82vtlnm05704rl.apps.googleusercontent.com";

function App() {
  useEffect(()=>
{
  function star(){
    gapi.client.init({
      clientId:clientId,
      scope:""
    })
  };
  gapi.load('client:auth2',star)
});
  return (
    <BrowserRouter>
      <div>
        <Header />
        <Main />
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
