<div class="panel-body">
     <div class="form-inline" >  
         <div class="input-group col-md-3" style="margin-top:0px positon:relative">
             <input type="text" class="form-control" name="selectName" id="selectName" placeholder="??" / >
                   <span class="input-group-btn">
                        <button id="selectSqlButton" class="btn btn-info btn-search">查找</button>
                   </span>
          </div>
      </div>
      <hr style="margin-top:5px"/>
      <!-- 表格,使用ajax返回刷新 -->			
      <div class="table-responsive" >
           <table class="table table-striped table-bordered">
             <thead>
               <tr>
                  <th>订单</th>
                  <th>桌号</th>
                  <th>菜树</th>
                  <th>金额</th>
                  <th>状态</th>
                  <th>操作</th>                  
                  <th>操作</th>								
               </tr>
           </thead>
         <tbody id="sqlTables">
         </tbody>					
         </table>
        </div>
					
 <!-- 每页显示几条记录 -->
 <div id="bottomTool" style="float:right">
     <label>每页
         <select id="pageSize" onchange="research()" size="1">
          <option selected="selected">10</option>
          <option>15</option>
          <option>20</option>
          <option>30</option>
         </select>条记录     
     </label>
     <label>显示第
       <label id="startItem"></label>至
       <label id="endItem"></label>记录,共
       <label id="allItem"></label>项     
     </label>
     <label>
        <a id="previousPage" style="color:gray">上一页</a>
         <select id="PageNumDetail" onchange="selectPage()" size="1">
         </select>
        <a id="nextPage" style="color:gray">下一页</a>
     </label>
 </div>
 </div>