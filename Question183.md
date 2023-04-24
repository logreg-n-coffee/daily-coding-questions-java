# Question 183

## Question

Describe what happens when you type a URL into your browser and press Enter.

## Answer

1. Parse the URL: The browser parses the URL to identify the protocol (e.g., http or https), the domain name, 
and any additional path or query parameters.
2. Perform a DNS lookup: The browser needs to convert the domain name into an IP address to identify the server 
hosting the web page. This is done through a Domain Name System (DNS) lookup. 
The browser first checks its local cache for any previously resolved IP addresses. 
If not found, it will query a DNS server, which may involve multiple steps until the IP address is retrieved.
3. Establish a connection: With the IP address identified, the browser initiates a connection with the server 
using the Transmission Control Protocol (TCP). If the website uses HTTPS, a secure connection is established 
using the Transport Layer Security (TLS) or Secure Sockets Layer (SSL) protocol. 
This involves a handshake process, which includes exchanging and verifying digital certificates to ensure the 
server's authenticity and agreeing on cryptographic keys to encrypt the data transmitted.
4. Send an HTTP request: The browser sends an HTTP request to the server, which includes the specific web page or 
resource it wants to retrieve. This request typically includes the HTTP method (usually GET), the path, 
query parameters, and headers containing additional information such as the browser type and accepted content types.
5. Server processes the request: The server processes the HTTP request, potentially involving various tasks such as 
database queries or server-side scripting. Once the requested resource is generated or retrieved, the server sends 
an HTTP response back to the browser.
6. Receive and process the HTTP response: The browser receives the HTTP response, which contains a status code 
indicating the result of the request (e.g., 200 OK, 404 Not Found), headers with additional information, and the 
requested content, usually in the form of HTML, CSS, JavaScript, or media files.
7. Render the web page: The browser processes the HTML, CSS, and JavaScript to construct the Document Object Model 
(DOM) and render the web page. This involves downloading additional resources (such as images, fonts, or videos) by 
making additional HTTP requests if necessary. 
JavaScript may also be executed to add interactivity or manipulate the DOM dynamically.
8. Display the web page: Once the DOM is constructed and the resources are loaded, the browser displays 
the web page on your screen.