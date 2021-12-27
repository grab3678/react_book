import {Container} from "react-bootstrap";
import {Route, Routes} from "react-router-dom";
import Header from "./components/Header";
import SaveForm from "./pages/book/SaveForm";
import Detail from "./pages/book/Detail";
import LoginForm from "./pages/user/LoginForm";
import JoinForm from "./pages/user/JoinForm";
import UpdateForm from "./pages/book/UpdateForm";
import Home from "./pages/book/Home";

function App() {
  return (
      <div>
          <Header/>
        <Container>
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route path="/saveForm" element={<SaveForm/>} />
            <Route path="/book/:id" element={<Detail/>} />
            <Route path="/loginForm" element={<LoginForm/>} />
            <Route path="/joinForm" element={<JoinForm/>} />
            <Route path="/updateForm/:id" element={<UpdateForm/>} />
          </Routes>
        </Container>
      </div>
  )
}

export default App;
