<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events Comments</title>
<link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<style type="text/css">
table, td, th
{
border:1px solid green;
font-family: 'Oxygen', sans-serif;
}
th
{
background-color:green;
color:white;
}
body
{
 text-align: center;
}
.container
{
 margin-left: auto;
 margin-right: auto;
 width: 40em;
}
</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
 <script type="text/javascript">
$(function() {

$(".comment_button").click(function() {


   
    var boxval = $("#commentBox").val();
	
    var dataString = 'commentBox='+ boxval;
	
	if(boxval=='')
	{
	alert("Please Enter Some Text");
	
	}
    });
});
   </script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
 $("#tablediv").hide();
     $("#showTable").click(function(event){
           $.get('PopulateTable',function(responseJson) {
            if(responseJson!=null){
                $("#commentsTable").find("tr:gt(0)").remove();
                var table1 = $("#commentsTable");
                $.each(responseJson, function(key,value) { 
                     var rowNew = $("<tr><td></td><td></td><td></td></tr>");
                        rowNew.children().eq(0).text(value['author']); 
                        rowNew.children().eq(1).text(value['body']); 
                        rowNew.children().eq(2).text(value['datePosted']); 
                        rowNew.appendTo(table1);
                });
                }
            });
            $("#tablediv").show();          
  });      
});
</script>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#showTable2").click(function(event){
        $("#tablediv").hide();        
  });      
});
</script>

</head>
<body class="container">
<h1>Comment System in AJAX</h1>
<input type="button" value="Show Table" id="showTable"/>
<input type="button" value="Hide Table" id="showTable2"/>
<div id="tablediv">
<table cellspacing="0" id="commentsTable"> 
    <tr> 
        <th scope="col">Author</th> 
        <th scope="col">Body</th> 
        <th scope="col">Date</th>       
    </tr> 
</table>
    </div>

<textarea rows="4" cols="50" name="commentBox" id="commentBox" maxlength="145" ></textarea><br />
<input type="button" value="Submit Comment" name="submit" class="comment_button"/>
    
</body>
</html>
