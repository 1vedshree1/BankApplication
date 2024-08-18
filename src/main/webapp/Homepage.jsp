<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Banking Homepage</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Quicksand:400,500,700" rel="stylesheet">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
       
        html, body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Quicksand", sans-serif;
        }

       
        .navbar {
            background: rgba(255, 255, 255, 0.5); /* Transparent background */
            backdrop-filter: blur(10px);
            padding: 1rem;
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 1000;
        }
        .navbar a {
            color: #fff;
            text-decoration: none;
            margin: 0 1rem;
            font-size: 1.2rem;
        }
        .navbar a:hover {
            color: #00E676;
        }
        .navbar-brand img {
            height: 40px; /* Adjust the logo height as needed */
        }

        /* Background Image Styles */
        .background-image {
            background-image: url('https://media.istockphoto.com/id/1492189677/photo/bank-symbol-with-with-coins-stack-concepts-of-the-banking-system-rising-interest-rates.webp?b=1&s=170667a&w=0&k=20&c=4juSl-CA3dqdm7SoZzKMkOyABsMeIeNYoNKH-0zYDsc='); /* Provided image URL */
            background-size: cover;
            background-position: center;
            height: 100vh;
            width: 100%;
            position: relative;
            color: #fff;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .background-image h1 {
            font-size: 3rem;
            margin: 0;
        }
        .background-image p {
            font-size: 1.5rem;
        }

       
        #aboutUs {
            position: absolute;
            top: 40%;
            left: 5%;
            transform: translateY(-50%);
            background: rgba(0, 0, 0, 0.6);
            padding: 2rem;
            color: #fff;
            border-radius: 10px;
            max-width: 300px;
        }
        #aboutUs h2 {
            font-size: 2rem;
            margin-bottom: 1rem;
        }
        #aboutUs p {
            font-size: 1rem;
        }

       
        .navbar .nav-link {
            background-color: #ffd3ac; /* Dark peach color */
            color: #000000; 
            border-radius: 50px; 
            padding: 10px 20px; 
            font-size: 1rem; 
            transition: background-color 0.2s ease, transform 0.2s ease; /* Transition effects */
            text-align: center;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            height: 50px; 
        }
        .navbar .nav-link:hover {
            background-color: #ffd3ac; /* Darker peach on hover */
            transform: scale(1.05); /* Slightly scale up on hover */
        }

        /* Responsive Styles */
        @media (max-width: 768px) {
            .navbar a {
                font-size: 1rem;
            }
            .background-image h1 {
                font-size: 2.5rem;
            }
            .background-image p {
                font-size: 1.2rem;
            }
            #aboutUs {
                width: 90%;
                left: 50%;
                transform: translate(-50%, -50%);
                top: 60%;
                text-align: center;
            }
        }
    </style>
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="">
     <img src="logo.jpg" alt="Bank Logo">AuroBank</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal" data-target="#loginModal">
                    <i class="fa fa-user"></i> Login
                </a>
            </li>
        </ul>
    </div>
</nav>


<div class="background-image">
        <!-- About Us Section -->
    <div id="aboutUs">
        <h2>About Us</h2>
        <p>We are committed to providing the best financial services. Our bank offers a range of products designed to meet your financial needs. Learn more about our services, history, and our commitment to customer satisfaction.</p>
    </div>
</div>

<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel"> Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:include page="Login.jsp" />
            </div>
        </div>
    </div>
</div>

<!-- jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
