CREATE TABLE "products_suppliers" (
	"id"	INTEGER NOT NULL,
	"product_id"	INTEGER NOT NULL,
	"supplier_id"	INTEGER NOT NULL,
	"price"	NUMERIC NOT NULL,
	FOREIGN KEY("product_id") REFERENCES "products"("id"),
	FOREIGN KEY("supplier_id") REFERENCES "suppliers"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
)