-- ============================================================
-- SAMPLE DATA FOR LIBRARY MANAGEMENT SYSTEM
-- ============================================================
-- This file is automatically executed on application startup
-- Data is loaded into the H2 in-memory database

-- ===================== AUTHORS =====================
INSERT INTO authors (id, name, nationality, birth_year) VALUES 
(1, 'George Orwell', 'British', 1903),
(2, 'J.K. Rowling', 'British', 1965),
(3, 'Robert C. Martin', 'American', 1952);

-- ===================== BOOKS =====================
-- PhysicalBooks (book_type = 'PHYSICAL')
INSERT INTO books (id, title, isbn, publish_year, available, book_type, author_id, shelf_location, weight) VALUES 
(1, '1984', '978-0451524935', 1949, true, 'PHYSICAL', 1, 'A1-001', 0.35),
(2, 'Animal Farm', '978-0451526342', 1945, true, 'PHYSICAL', 1, 'A1-002', 0.25),
(3, 'Clean Code', '978-0132350884', 2008, true, 'PHYSICAL', 3, 'B2-015', 0.75);

-- EBooks (book_type = 'EBOOK')
INSERT INTO books (id, title, isbn, publish_year, available, book_type, author_id, file_format, download_link) VALUES 
(4, 'Harry Potter and the Philosophers Stone', '978-0747532699', 1997, true, 'EBOOK', 2, 'PDF', 'https://library.example.com/hp1.pdf'),
(5, 'The Agile Manifesto Guide', '978-0135974445', 2019, true, 'EBOOK', 3, 'EPUB', 'https://library.example.com/agile.epub');

-- ===================== BORROWINGS =====================
INSERT INTO borrowings (id, book_id, member_name, borrow_date, return_date, status) VALUES 
(1, 1, 'Alice Johnson', '2024-01-10', '2024-01-24', 'RETURNED'),
(2, 3, 'Bob Smith', '2024-01-15', '2024-01-29', 'ACTIVE');
