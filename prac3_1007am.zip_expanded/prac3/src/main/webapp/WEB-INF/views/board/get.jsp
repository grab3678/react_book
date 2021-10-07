<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>

<div class="row">
   <div class="col-lg-12">
      <h1 class="page-header">글 읽기</h1>
   </div>
</div>

<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">

         <div class="panel-heading"></div>
         <div class="panel-body">

            <div class="form-group">
               게시물 번호<input class="form-control" name="bno"
                  value='<c:out value="${board.bno }"/>' readonly="readonly">
            </div>
            <div class="form-group">
               제목<input class="form-control" name="title"
                  value='<c:out value="${board.title }"/>' readonly="readonly">
            </div>
            <div class="form-group">
               내용
               <textarea rows="3" class="form-control" name="content"
                  readonly="readonly"><c:out value="${board.content }" /></textarea>
            </div>
            <div class="form-group">
               작성자<input class="form-control" name="writer"
                  value='<c:out value="${board.writer }"/>' readonly="readonly">
            </div>
            <button data-oper="modify" id="boardModBtn" class="btn btn-warning">수정</button>
            <button data-oper="list" id="boardListBtn" class="btn btn-info">목록</button>

            <form id='operForm' action="/board/modify" method="get">
               <input type='hidden' id='bno' name='bno' value="${board.bno }" />
               <input type="hidden" name="pageNum" value="${cri.pageNum }" /> <input
                  type="hidden" name="amount" value="${cri.amount }" /> <input
                  type="hidden" name="type" value="${cri.type }"> <input
                  type="hidden" name="keyword" value="${cri.keyword }">
            </form>

         </div>

      </div>
   </div>
</div>

<!-- 덧글 목록 시작 -->
<br />
<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
         <div class="panel-heading">
            <i class="fa fa-comments fa-fw"></i>덧글
            <button id="addReplyBtn" class="btn btn-primary btn-xs float-right">
               새 덧글</button>
         </div>
         <br />
         <div class="panel-body">
            <ul class="chat">
               <li>덧글 예시</li>
            </ul>
         </div>
         <div class="panel-footer"></div>
      </div>
   </div>
</div>
<!-- 덧글 목록 끝 -->

<!-- 덧글 입력 모달창 시작 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
   aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            &times;
            <h4 class="modal-title" id="myModalLabel">덧글 창</h4>
         </div>
         <div class="modal-body">
            <div class="form-group">
               <label>덧글</label> <input class="form-control" name="reply"
                  value="새 덧글">
            </div>
            <div class="form-group">
               <label>작성자</label> <input class="form-control" name="replyer"
                  value="replyer">
            </div>
            <div class="form-group">
               <label>덧글 작성일</label> <input class="form-control" name="replyDate"
                  value="">
            </div>
         </div>
         <div class="modal-footer">
            <button id="modalModBtn" type="button" class="btn btnwarning">수정
            </button>
            <button id="modalRemoveBtn" type="button" class="btn btndanger">삭제
            </button>
            <button id="modalRegisterBtn" type="button" class="btn btnprimary">등록
            </button>
            <button id="modalCloseBtn" type="button" class="btn btndefault">닫기
            </button>
         </div>
      </div>
   </div>
</div>
<!-- 덧글 입력 모달창 끝 -->




<script type="text/javascript" src="/resources/js/reply.js">
   
</script>

<script>
   $(document).ready(function() {
      //console.log(replyService);
      var bnoValue = '<c:out value="${board.bno}"/>';// 글번호

      // 함수를 담고있는 변수 replyService의 add 함수 호출.
      // 하면서 replyVO 객체와 콜백 메소드 전달.
      // 새 덧글 처리가 완료 된다면, 콜백 메소드가 구동함.
      /* replyService.add({
         reply : "js test",
         replyer : "tester",
         bno : bnoValue
      }, function(result) {
         alert("result: " + result);
      }); */
      // 게시글을 읽을때 자동으로 댓글 1개 등록.
      var modal = $("#myModal");
      // 덧글 용 모달.
      var modalInputReplyDate = modal.find("input[name='replyDate']");
      // 덧글 작성일 항목.
      var modalRegisterBtn = $("#modalRegisterBtn");
      // 모달에서 표시되는 덧글쓰기 버튼.
      var modalInputReply = modal.find("input[name='reply']");
      // 덧글 내용
      var modalInputReplyer = modal.find("input[name='replyer']");// 덧글 작성자.

      // 덧글 입력 모달 표시하기.
      $("#addReplyBtn").on("click", function(e) {
         // 덧글 쓰기 버튼을 클릭한다면,
         e.preventDefault();
         modal.find("input").val("");
         // 모달의 모든 입력창을 초기화
         modalInputReplyDate.closest("div").hide();
         // closest : 선택 요소와 가장 가까운 요소를 지정.
         // 즉, modalInputReplyDate 요소의 가장 가까운
         // div를 찾아서 숨김. (날짜창 숨김)
         modal.find("button[id != 'modalCloseBtn']").hide();
         // 모달창에 버튼이 4개 인데, 닫기 버튼을 제외하고 숨기기.
         modalRegisterBtn.show(); // 등록 버튼은 보여라.
         $("#myModal").modal("show");// 모달 표시.
      });
      
      // 모달창 닫기
      $("#modalCloseBtn").on("click", function(e) {
         modal.modal("hide");
         // 모달 닫기 라는 버튼을 클릭한다면 모달창을 숨김.
      });
      
      // 덧글 쓰기.
      modalRegisterBtn.on("click", function(e) {
         // 덧글 등록 버튼을 눌렀다면,
         var reply = {
            reply : modalInputReply.val(),
            replyer : modalInputReplyer.val(),
            bno : bnoValue
         }; // ajax로 전달할 reply 객체 선언 및 할당.
         replyService.add(reply, function(result) {
            alert(result);
            // ajax 처리후 결과 리턴.
            modal.find("input").val("");
            // 모달창 초기화
            modal.modal("hide");// 모달창 숨기기
            showList(-1);
         });
      });
      
      // 덧글 목록 콘솔에 보이기.
      /* replyService.getList({
         bno : bnoValue,
         page : 1
      }, function(list) {
         for (var i = 0, len = list.length || 0; i < len; i++) {
            console.log(list[i]);
         }
      }); */
      var replyUL = $(".chat");
      // reply Unorderd List
      
      function showList(page) {
                     replyService
                           .getList(
                                 {
                                    bno : bnoValue,
                                    page : page || 1
                                 },
                                 function(replyTotalCnt, list) {
                                    console.log("replyTotalCnt :"+ replyTotalCnt);
                                    if (page == -1) {
                                       // 페이지 번호가 음수 값 이라면,
                                       pageNum = Math.ceil(replyTotalCnt / 10.0);
                                       // 덧글의 마지막 페이지 구하기.
                                       showList(pageNum);
                                       // 덧글 목록 새로고침(갱신)
                                       return;
                                    }                                    
                                    
                                    var str = "";
                                    if (list == null
                                          || list.length == 0) {
                                       replyUL.html("");
                                       return;
                                    }
                     for (var i = 0, len = list.length || 0; i < len; i++) {
                     str+= "<li class='left ";
                     str+= "clearfix' data-rno='";
                     str+= list[i].rno+"'>";
                     str+= "<div><div class='header' ";
                     str+= "><strong class='";
                     str+= "primary-font'>";
                     str+= list[i].replyer+ "</strong>";
                     str+= "<small class='float-sm-right '>";
            str+= replyService.displayTime(list[i].replyDate)+ "</small></div>";
                     str+= "<p>"+ list[i].reply;
                     str+= "</p></div></li>";
                                    }
                                    replyUL.html(str);
                                    showReplyPage(replyTotalCnt);
                                 });
                  }

                  showList(-1);
                  
                  /* 덧글 페이징 시작 */
                  var pageNum = 1;
                  var replyPageFooter = $(".panel-footer");

                  function showReplyPage(replyCnt) {
                     var endNum = Math.ceil(pageNum / 10.0) * 10;
                     // pageNum : 1 이라고 가정하면,
                     // Math.ceil(1/10.0) 처리하고 *10, 즉 endNum : 10
                     var startNum = endNum - 9;// - 나올지도..
                     var prev = startNum != 1;// false = (1 !=1)
                     var next = false;
                     // replyCnt : 384, endNum : 39
                     if (endNum * 10 >= replyCnt) {// 100 >= 384
                        endNum = Math.ceil(replyCnt / 10.0);
                     }
                     if (endNum * 10 < replyCnt) {
                        next = true;
                     }
                     var str = "<ul class='pagination";
                     str+=" justify-content-center'>";
                     if (prev) {
                        str += "<li class='page-item'><a ";
                        str += "class='page-link' href='";
                        str += (startNum - 1);
                        str += "'>이전</a></li>";
                     }
                     for (var i = startNum; i <= endNum; i++) {
                        var active = pageNum == i ? "active" : "";
                        str += "<li class='page-item "+ active
                        +"'><a class='page-link' ";
                        str+="href='"+i+"'>"
                        + i + "</a></li>";
                     }
                     if (next) {
                        str += "<li class='page-item'>";
                        str += "<a class='page-link' href='";
                        str += (endNum + 1) + "'>다음</a></li>";
                     }
                     str += "</ul>";
                     console.log(str);
                     replyPageFooter.html(str);
                  }
                  /* 덧글 페이징 끝 */


      /* 문서가 준비 됐다면, 아래 함수 수행. */
      var formObj = $("form");/* 문서중 form 요소를 찾아서 변수에 할당. */

      $("#boardModBtn").on("click", function(e) {
         e.preventDefault();
         var operation = $(this).data("oper");
         /* 버튼에서 oper 속성 읽어서 변수에 할당. */
         console.log(operation);
         /* 브라우저 로그로 oper값 출력. */
         if (operation === 'modify') {
            formObj.attr("action", "/board/modify");
            /* form에 액션 속성을 변경. */
         }
         formObj.submit();
      });

      $("#boardListBtn").on("click", function(e) {
         e.preventDefault();
         var operation = $(this).data("oper");
         /* 버튼에서 oper 속성 읽어서 변수에 할당. */
         console.log(operation);
         /* 브라우저 로그로 oper값 출력. */
         if (operation === 'list') {
            //self.location = "/board/list";
            //return;
            formObj.attr("action", "/board/list");
            formObj.find("#bno").remove();
            // form 에서 아이디 bno 요소를 찾아서 삭제.
         }
         formObj.submit();
      });
      
      var modalModBtn = $("#modalModBtn");
      var modalRemoveBtn = $("#modalRemoveBtn");
      
      
      $(".chat").on("click", "li", function(e) {
         //클래스 chat 을 클릭하는데, 하위 요소가 li라면,
         var rno = $(this).data("rno");
         // 덧글에 포함된 값들 중에서 rno를 추출하여 변수 할당.
         console.log(rno);
         
         replyService.get(rno,function(reply) {
            modalInputReply.val(reply.reply);
            modalInputReplyer.val(reply.replyer);
            modalInputReplyDate.val(replyService.displayTime(reply.replyDate))
                  .attr("readonly","readonly");
            // 댓글 목록의 값들을 모달창에 할당.
            modal.data("rno",reply.rno);
            // 표시되는 모달창에 rno 라는 이름으로 data-rno를 저장.
            modal.find("button[id !='modalCloseBtn']").hide();
            modalModBtn.show();
            modalRemoveBtn.show();
            // 버튼 보이기 설정.
            $("#myModal").modal("show");
         });
      });
      
      // 댓글 수정 버튼을 누른다면,
      modalModBtn.on("click", function(e) {
         var reply = {
            rno : modal.data("rno"),
            reply : modalInputReply.val()
         };
         replyService.update(reply, function(result) {
            alert(result);
            modal.modal("hide");
            showList(-1);
         });
      });
      
      // 덧글 삭제 처리.
      modalRemoveBtn.on("click", function(e) {
         var rno = modal.data("rno");
         replyService.remove(rno, function(result) {
            alert(result);
            modal.modal("hide");
            showList(-1);
         });
      });

      //덧글 페이징 클릭시 처리.
      replyPageFooter.on("click", "li a", function(e){
    	  e.preventDefault();
    	  var targetPageNum = $(this).attr("href");
    	  pageNum = targetPageNum;
    	  showList(pageNum);
      });
      

   });// end_document_Ready
</script>