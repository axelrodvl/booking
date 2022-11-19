import React from "react";

import 'antd/dist/reset.css';
import './App.css'

import Landing from "./page/Landing";

import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Landing/>}/>
                {/*<Route path="/signup" element={<SignUp/>}/>*/}
            </Routes>
        </BrowserRouter>
    );
}

export default App;
