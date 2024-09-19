<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="errors.jsp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action=${customer != null ? "update" : "insert"} method="post">
    <h2>
        <c:if test="${customer != null}">Edit Customer</c:if>
        <c:if test="${customer == null}">Add New Customer</c:if>
    </h2>
    <table class="table">
        <c:if test="${customer != null}">
            <input type="hidden" name="id"
                value="<c:out value='${customer.id}' />" />
        </c:if>
        <tr>
            <th>Customer Name:</th>
            <td><input type="text" name="name" size="45"
                value="<c:out value='${customer.name}' />" /></td>
        </tr>
        <tr>
            <th>Customer Email:</th>
            <td><input type="text" name="email" size="45"
                value="<c:out value='${customer.email}' />" /></td>
        </tr>
        <tr>
            <th>Country:</th>
            <td><select name="country" id="country"
                value="<c:out value='${customer.country.name}' />">
                    <c:forEach var="country" items="${listCountries}">
                        <option value="${country.id}" ${customer.country.name.equals(country.name) ? 'selected' : ''}>${country.name}</option>
                    </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit"
                value="Save" /></td>
        </tr>
    </table>
</form>