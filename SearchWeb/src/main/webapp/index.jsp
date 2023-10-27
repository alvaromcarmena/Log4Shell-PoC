<html>

	<style>	
		body {
    		text-align: center;
		}
		form {
    		display: inline-block;
		}
	    input[name="searchTerm"]{
	    	font-size: 18px;
            width: 360px;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
       	input[type="submit"]{
            padding: 10px 20px;
			font-size: 16px;
        }
	</style>
	<body>
		<form action="search" method="POST">
			<input type="text" name="searchTerm" placeholder="Enter your search here">
			<input type="submit" value="Search">
		</form>
	</body>
</html>
