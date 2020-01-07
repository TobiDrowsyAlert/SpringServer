<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 2019-12-23
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="../dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">Alexander Pierce</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item has-treeview menu-open"> <!-- 항목 시작 -->
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-edit"></i>
              <p>
                메뉴
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${path}/article/list" class="nav-link active">
                  <i class="fa fa-list"></i>
                  <p>게시판</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${path}/article/listCriteria" class="nav-link active">
                  <i class="fa fa-list"></i>
                  <p>게시판 페이징처리</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${path}/article/listPaging" class="nav-link active">
                  <i class="fa fa-list"></i>
                  <p>게시판 페이징, 페이징 번호 </p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${path}/article/listPagingRead" class="nav-link active">
                  <i class="fa fa-list"></i>
                  <p>게시판 페이징, 페이징 번호, 정보 유지 </p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${path}/article/write" class="nav-link active">
                  <i class="fa fa-pencil-alt"></i>
                  <p>게시판 쓰기</p>
                </a>
              </li>
            </ul>
          </li> <!-- nav-item has-treeview menu-open -->
          <li class="nav-item has-treeview menu-open"> <!-- 항목 시작 -->
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-edit"></i>
              <p>
                게시판
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${path}/article/list" class="nav-link active">
                  <i class="fa fa-list"></i>
                  <p>게시판</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${path}/article/listCriteria" class="nav-link active">
                  <i class="fa fa-list"></i>
                  <p>게시판 페이징처리</p>
                </a>
              </li>
            </ul>
          </li> <!-- nav-item has-treeview menu-open -->
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Simple Link
                <span class="right badge badge-danger">New</span>
              </p>
            </a>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

