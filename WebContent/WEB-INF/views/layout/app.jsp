<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>メッセージボード</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
      <div id="wrappter">
          <div id="header">
            <h1>メッセージボード アプリケーション</h1>
          </div>
          <div id="content">
            ${param.content}
          </div>
          <div id="footer">
            by Yuta Matsuo
          </div>
      </div>
    </body>
</html>