
import './App.css';


import Login from "./pages/Login";
import {Route, Router} from "react-router-dom";
import Main from "./pages/Main";
import { createBrowserHistory } from 'history'
function App() {

  return (

    <div className="App">
        <Router history={createBrowserHistory()}>
            <Route  path="/main" component={Main} />
            <Route  exact path="/" component={Login} />
        </Router>

    </div>
  );
}

export default App;
