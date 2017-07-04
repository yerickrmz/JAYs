/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Usuario
 */
public class xPath {

    private String xmlFile = "IF3000.xml";
    private FileInputStream file;
    private DocumentBuilderFactory builderFactory;
    private DocumentBuilder builder;
    private Document xmlDocument;
    private XPath xPath;

    public xPath() {
        loadFile();
    }

    public xPath(String xmlFile) {
        this.xmlFile = xmlFile;
        loadFile();
    }

    private void loadFile() {
        try {
            file = new FileInputStream(new File(xmlFile));
            builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();
            xmlDocument = builder.parse(file);
            xPath = XPathFactory.newInstance().newXPath();
        } catch (ParserConfigurationException ex) {
            System.err.println("loadFile method, ParserConfigurationException: " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        } catch (SAXException ex) {
            System.err.println("loadFile method, SAXException: " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            System.err.println("loadFile method, IOException: " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        }
    }

    public boolean addNewStudent(String id, String firstName, String middleName, String lastName, String secondLastName, String email, int group) {
        try {
            String expression = String.format("/course/student[@studentId='%s']/@studentId", id);
            String idFound = (String) xPath.compile(expression).evaluate(xmlDocument);

            if (idFound.equals("")) {

                Element newStudent = xmlDocument.createElement("student");

                newStudent.setAttribute("studentId", id);

                Element newName = xmlDocument.createElement("name");

                Element newFirstName = xmlDocument.createElement("firstName");
                newFirstName.appendChild(xmlDocument.createTextNode(firstName));

                Element newMiddleName = xmlDocument.createElement("middleName");
                newMiddleName.appendChild(xmlDocument.createTextNode(middleName));

                Element newLastName = xmlDocument.createElement("lastName");
                newLastName.appendChild(xmlDocument.createTextNode(lastName));

                Element newSecondLastName = xmlDocument.createElement("secondLastName");
                newSecondLastName.appendChild(xmlDocument.createTextNode(secondLastName));

                Element newEmail = xmlDocument.createElement("email");
                newEmail.appendChild(xmlDocument.createTextNode(email));

                Element newGroup = xmlDocument.createElement("group");
                newGroup.appendChild(xmlDocument.createTextNode(group + ""));

                newName.appendChild(newFirstName);
                newName.appendChild(newMiddleName);
                newName.appendChild(newLastName);
                newName.appendChild(newSecondLastName);
                newStudent.appendChild(newName);
                newStudent.appendChild(newEmail);
                newStudent.appendChild(newGroup);

                Node rootNode = xmlDocument.getElementsByTagName("course").item(0);
                rootNode.appendChild(newStudent);

                // write the DOM object to the file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();

                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(xmlDocument);

                StreamResult streamResult = new StreamResult(new File(xmlFile));
                transformer.transform(domSource, streamResult);
                return true;
            }
        } catch (XPathExpressionException ex) {
            System.err.println("addNewStudent method, XPathExpressionException: " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        } catch (TransformerException ex) {
            System.err.println("addNewStudent method, TransformerException: " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        }
        return false;
    }

    public boolean deleteStudentById(String id) {
        try {
            String expression = String.format("/course/student[@studentId='%s']", id);

            Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);

            if (node != null) {

                Node rootNode = xmlDocument.getElementsByTagName("course").item(0);
                rootNode.removeChild(node);

                // write the DOM object to the file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();

                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(xmlDocument);

                StreamResult streamResult = new StreamResult(new File(xmlFile));
                transformer.transform(domSource, streamResult);
                return true;
            }
        } catch (XPathExpressionException ex) {
            System.err.println("deleteStudentById method, XPathExpressionException: " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        } catch (TransformerException ex) {
            System.err.println("deleteStudentById method, TransformerException: " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        }
        return false;
    }

}
