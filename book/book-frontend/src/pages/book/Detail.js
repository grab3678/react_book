import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router";
import {Button} from "react-bootstrap";

const Detail = () => {

    const navigate = useNavigate();
    let { id } = useParams();

    const [book, setBook] = useState({
        id: '',
        title: '',
        author: ''
    });

    useEffect(() => {
        fetch("http://localhost:9091/book/" + id)
            .then(res => res.json())
            .then(res => {
                setBook(res);
            });
    }, []);

    const deleteBook = (id) => {
        fetch("http://localhost:9091/book/" + id,
            {method: "DELETE"}).then(res => res.text()).then(res => {
                if (res === "ok") {
                    navigate("/");
                } else {
                    alert("삭제 실패");
                };
            }
        )
    };

    const updateBook = () => {
      navigate("/updateForm/"+id);
    }

    return (
        <div>
            <h1>상세 보기</h1>
            <Button variant="warning" onClick={updateBook}>수정</Button>
            {'  '}
            <Button variant="danger" onClick={deleteBook}>삭제</Button>

            <hr/>
            <h3>{book.author}</h3>
            <h1>{book.title}</h1>
        </div>
    );
};

export default Detail;