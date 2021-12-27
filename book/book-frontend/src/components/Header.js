import React from 'react';
import { Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <>
            <Navbar bg="dark" variant="dark">
                <Link to="/" className="navbar-brand">홈</Link>
                <Nav className="me-auto">
                    <Link to="/joinForm" className="nav-link">회원 가입</Link>
                    <Link to="/loginForm" className="nav-link">로그인</Link>
                    <Link to="/saveForm" className="nav-link">글쓰기</Link>
                </Nav>
            </Navbar>
            <br/>
        </>
    );
};

export default Header;