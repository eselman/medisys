-- tabla pais (country)
CREATE TABLE country (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	name VARCHAR(255),
	PRIMARY Key(id),
	UNIQUE country_code_unique (code)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla provincia (county)
CREATE TABLE county (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	name VARCHAR(255),
	country_id INT NOT NULL,
	PRIMARY Key(id),
	UNIQUE county_code_unique (code),
	CONSTRAINT fk_county_country FOREIGN KEY (country_id)
	REFERENCES country(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla departamento (department)
CREATE TABLE department (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	name VARCHAR(255),
	county_id INT NOT NULL,
	country_id INT NOT NULL,
	PRIMARY Key(id),
	UNIQUE department_code_unique (code),
	CONSTRAINT fk_department_county FOREIGN KEY (county_id)
	REFERENCES county(id),
	CONSTRAINT fk_department_country FOREIGN KEY (country_id)
	REFERENCES country(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla localidad (town)
CREATE TABLE town (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	department_id INT NOT NULL,
	county_id INT NOT NULL,
	country_id INT NOT NULL,
	name VARCHAR(255),
	PRIMARY Key(id),
	UNIQUE town_code_unique (code),
	CONSTRAINT fk_town_department FOREIGN KEY (department_id)
	REFERENCES department(id),
	CONSTRAINT fk_town_county FOREIGN KEY (county_id)
	REFERENCES county(id),
	CONSTRAINT fk_town_country FOREIGN KEY (country_id)
	REFERENCES country(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla domicilio (address)
CREATE TABLE address (
	id INT NOT NULL AUTO_INCREMENT,
	street VARCHAR(255) NOT NULL,
	number VARCHAR(10),
	floor VARCHAR(10),
	apartment VARCHAR(10),
	town_id INT NOT NULL,
	department_id INT NOT NULL,
	county_id INT NOT NULL,
	country_id INT NOT NULL,
	PRIMARY Key(id),
	CONSTRAINT fk_address_town FOREIGN KEY (town_id)
	REFERENCES town(id),
	CONSTRAINT fk_address_department FOREIGN KEY (department_id)
	REFERENCES department(id),
	CONSTRAINT fk_address_county FOREIGN KEY (county_id)
	REFERENCES county(id),
	CONSTRAINT fk_address_country FOREIGN KEY (country_id)
	REFERENCES country(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla obra social (medical_insurance)
CREATE TABLE medical_insurance (
	id INT NOT NULL AUTO_INCREMENT,
	symbol VARCHAR(10) NOT NULL,
	description VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE symbol_unique (symbol)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla tipo de estudio (medical_test_type)
CREATE TABLE medical_test_type (
	id INT NOT NULL AUTO_INCREMENT,
	symbol VARCHAR(10) NOT NULL,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE medical_test_symbol_unique (symbol)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla tipo de documento (identification)
CREATE TABLE identification_type (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(15),
	name VARCHAR(255),
	PRIMARY KEY(id),
	UNIQUE code_unique (code)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla persona (person)
CREATE TABLE person (
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	birth_date DATE,
	identification_number VARCHAR(255) NOT NULL,
	identification_type_id INT NOT NULL,
	phone_number VARCHAR(15),
	mobile_phone VARCHAR(15),
	address_id INT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_person_identification_type FOREIGN KEY (identification_type_id)
	REFERENCES identification_type (id),
	CONSTRAINT fk_person_address FOREIGN KEY (address_id)
	REFERENCES address(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla laboratorio (laboratory)
CREATE TABLE laboratory (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla unidad de medida (measure_unit)
CREATE TABLE measure_unit (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(15) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE code_unique (code)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla tipo de presentacion (presentation_type)
CREATE TABLE presentation_type (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- tabla presentacion de un medicamento (presentation)
CREATE TABLE presentation (
	id INT NOT NULL AUTO_INCREMENT,
	quantity INT NOT NULL,
	dose DECIMAL NOT NULL,
	measure_unit_id INT NOT NULL,
	presentation_type_id INT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_presentation_measure_unit FOREIGN KEY (measure_unit_id)
	REFERENCES measure_unit (id),
	CONSTRAINT fk_presentation_presentation_type FOREIGN KEY (presentation_type_id)
	REFERENCES presentation_type(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla medicamento (medicine)
CREATE TABLE medicine (
	id INT NOT NULL AUTO_INCREMENT,
	brand_name VARCHAR(255) NOT NULL,
	generic_name VARCHAR(255) NOT NULL,
	laboratory_id INT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_medicine_laboratory FOREIGN KEY (laboratory_id)
	REFERENCES laboratory (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- relacion entre medicamento y sus presentaciones.
CREATE TABLE medicine_presentation (
	medicine_id INT NOT NULL,
	presentation_id INT NOT NULL,
	PRIMARY KEY (medicine_id, presentation_id),
	CONSTRAINT fk_medicine_presentation_medicine_id FOREIGN KEY (medicine_id)
	REFERENCES medicine (id),
	CONSTRAINT fk_medicine_presentation_presentation_id FOREIGN KEY (presentation_id)
	REFERENCES presentation(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla especialidad (specialty)
CREATE TABLE specialty (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE name_unique (name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- tabla doctor (doctor)
CREATE TABLE doctor (
	id INT NOT NULL AUTO_INCREMENT,
	specialty_id INT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_doctor_specialty FOREIGN KEY (specialty_id)
	REFERENCES specialty(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla paciente (patient)
CREATE TABLE patient (
	id INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- tabla relacional entre paciente y obra social (patient_insurance)
CREATE TABLE person_insurance (
	patient_id INT NOT NULL,
	insurance_id INT NOT NULL,
	affiliate_number VARCHAR(255),
	PRIMARY KEY (patient_id, insurance_id),
	CONSTRAINT fk_patient_insurance_patient_id FOREIGN KEY (patient_id)
	REFERENCES patient (id),
	CONSTRAINT fk_person_insurance_insurance_id FOREIGN KEY (insurance_id)
	REFERENCES medical_insurance(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla historia clinica (patient_history)
CREATE TABLE patient_history (
	id INT NOT NULL AUTO_INCREMENT,
	patient_id INT NOT NULL,
	doctor_id INT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_patient_history_patient_id FOREIGN KEY (patient_id)
	REFERENCES patient(id),
	CONSTRAINT fk_patient_history_doctor_id FOREIGN KEY (doctor_id)
	REFERENCES patient(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla detalle de historia clinica (patient_history_detail)
CREATE TABLE patient_history_detail (
	id INT NOT NULL AUTO_INCREMENT,
	patient_history_id INT NOT NULL,
	date DATE NOT NULL,
	weight DECIMAL NOT NULL,
	symptoms VARCHAR(255),
	diagnosis VARCHAR(255),
	observations VARCHAR(255),
	PRIMARY KEY (id),
	CONSTRAINT fk_patient_history_detail_history FOREIGN KEY (patient_history_id)
	REFERENCES patient_history (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla relacional entre detalle de historia clinica y medicamentos.
CREATE TABLE patient_history_detail_medicine (
	id INT NOT NULL AUTO_INCREMENT,
	patient_history_detail_id INT NOT NULL,
	medicine_id INT NOT NULL,
	prescription VARCHAR(255),
	PRIMARY KEY (id),
	CONSTRAINT fk_patient_history_detail_id FOREIGN KEY (patient_history_detail_id)
	REFERENCES patient_history_detail (id),
	CONSTRAINT fk_medicine_id FOREIGN KEY (medicine_id)
	REFERENCES medicine (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- tabla estudios (medical_test)
CREATE TABLE medical_test (
	id INT NOT NULL AUTO_INCREMENT,
	test_date DATE NOT NULL,
	test_type_id INT NOT NULL,
	result VARCHAR(255) NOT NULL,
	patient_history_detail_id INT NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_medical_test_test_type FOREIGN KEY (test_type_id)
	REFERENCES medical_test_type (id),
	CONSTRAINT fk_medical_test_patient_history_detail FOREIGN KEY (patient_history_detail_id)
	REFERENCES patient_history_detail (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;