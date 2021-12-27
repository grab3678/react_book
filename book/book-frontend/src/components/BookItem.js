import React from 'react';
import {Card} from "react-bootstrap";
import {Link} from "react-router-dom";

const BookItem = (props) => {
    const {id, title, author} = props.book;
    return (
        <Card.Body>
            <Card.Title>{title}</Card.Title>
            <Link to ={"/book/"+id} className="btn btn-primary" variant="primary">상세 보기</Link>
        </Card.Body>
    );
};

export default BookItem;