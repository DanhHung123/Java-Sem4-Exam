
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100">
<header class="w-full h-[80px] flex items-center justify-between bg-white px-8 shadow-md">
    <div class="h-full flex items-center">
        <img class="h-[70%] object-cover" src="https://cdn.onlinewebfonts.com/svg/img_110816.png" alt="logo">
        <h1 class="text-3xl font-bold ml-5">Management</h1>
    </div>
    <ul class="flex items-center text-xl">
        <li><a class="py-2 px-3" href="">Create</a></li>
        <li><a class="py-2 px-3" href="">Create</a></li>
        <li><a class="py-2 px-3" href="">Create</a></li>
    </ul>
</header>
<main class="max-w-[1200px] mx-auto min-h-[calc(100vh-80px)]">
    <div class="py-6 border-b border-gray-300">
        <h3 class="font-semibold text-center text-2xl">
            <c:if test="${employee != null}">
                Edit Employee
            </c:if>
            <c:if test="${employee == null}">
                Add New Employee
            </c:if>
        </h3>
    </div>

    <div class="flex justify-center py-10">
        <div class="bg-white w-[700px] rounded-md shadow-md p-6 mt-6">
            <c:if test="${employee != null}">
            <form action="update" method="post">
            </c:if>
            <c:if test="${employee == null}">
            <form action="insert" method="post">
            </c:if>
                <c:if test="${employee != null}">
                    <input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
                </c:if>
                <div class="mb-6">
                    <label for="fullName" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Full Name</label>
                    <input type="text" id="fullName" name="fullName"  value="<c:out value='${employee.fullName}' />"
                           class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
                           placeholder="Enter full name" required>
                </div>
                <div class="mb-6">
                    <label for="birthDay" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Birthday</label>
                    <input type="text" id="birthDay" name="birthDay" value="<c:out value='${employee.birthDay}' />"
                           placeholder="Enter birth day"
                           class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
                           required>
                </div>
                <div class="mb-6">
                    <label for="address" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Address</label>
                    <input type="text" id="address" name="address" value="<c:out value='${employee.address}' />"
                           placeholder="Enter address"
                           class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
                           required>
                </div>
                <div class="mb-6">
                    <label for="position" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Position</label>
                    <input type="text" id="position" name="position" value="<c:out value='${employee.position}' />"
                           placeholder="Enter position"
                           class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
                           required>
                </div>
                <div class="mb-6">
                    <label for="department" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Department</label>
                    <input type="text" id="department" name="department" value="<c:out value='${employee.department}' />"
                           placeholder="Enter department"
                           class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
                           required>
                </div>
                <div>
                    <button type="submit"
                            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center ">

                        <c:if test="${employee != null}">
                            Create new employee
                            </c:if>
                            <c:if test="${employee == null}">
                            Update employee
                                </c:if>
                    </button>
                    <button type="reset"
                            class="text-white bg-yellow-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center ">
                        Reset
                    </button>
                </div>
            </form>
        </div>
    </div>

</main>
</body>

</html>
