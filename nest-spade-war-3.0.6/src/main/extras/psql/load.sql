--
-- Name: dispatch; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE dispatch (
    dispatchkey integer NOT NULL,
    whenabandoned timestamp with time zone,
    whencompleted timestamp with time zone,
    whenstarted timestamp with time zone,
    destination_neighborkey integer NOT NULL,
    ticketedfile_ticketedfilekey integer NOT NULL
);


--
-- Name: entry; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE entry (
    entrykey integer NOT NULL,
    identity character varying(255),
    whenlastmodified timestamp with time zone,
    compressedpath_pathkey integer,
    datapath_pathkey integer,
    metapath_pathkey integer,
    wrappedpath_pathkey integer
);


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: hierarchicalpath; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE hierarchicalpath (
    pathkey integer NOT NULL,
    cachedpath character varying(255),
    name character varying(255),
    parent_pathkey integer NOT NULL
);


--
-- Name: inboundregistration; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE inboundregistration (
    remoteid character varying(255),
    registrationkey integer NOT NULL,
    deliverer_neighborkey integer NOT NULL
);


--
-- Name: localregistration; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE localregistration (
    registrationkey integer NOT NULL,
    localid character varying(255)
);


--
-- Name: neighbor; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE neighbor (
    neighborkey integer NOT NULL,
    email character varying(255)
);


--
-- Name: slicedexecution; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE slicedexecution (
    slicedexecutionkey integer NOT NULL,
    bytes bigint NOT NULL,
    count integer NOT NULL,
    interval_intervalkey integer NOT NULL,
    topic_topickey integer NOT NULL
);


--
-- Name: slicedinterval; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE slicedinterval (
    intervalkey integer NOT NULL,
    duration bigint NOT NULL,
    whenended timestamp with time zone NOT NULL
);


--
-- Name: slicedtopic; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE slicedtopic (
    topickey integer NOT NULL,
    description character varying(255),
    "interval" bigint NOT NULL
);


--
-- Name: ticketedfile; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE ticketedfile (
    ticketedfilekey integer NOT NULL,
    archivesuffix character varying(255),
    binarysize bigint,
    bundle character varying(255) NOT NULL,
    checksum bigint,
    compressedsize bigint,
    metadatasize bigint,
    packagedsize bigint,
    placedsize bigint,
    placementid character varying(255),
    remotenumber integer,
    remoteregistrationid character varying(255),
    whenabandoned timestamp with time zone,
    whenarchived timestamp with time zone,
    whenbinaryset timestamp with time zone,
    whencompleted timestamp with time zone,
    whencompressedset timestamp with time zone,
    whenflushed timestamp with time zone,
    whenmetadataset timestamp with time zone,
    whenpackagedset timestamp with time zone,
    whenplaced timestamp with time zone,
    whenrestored timestamp with time zone,
    whenticketed timestamp with time zone,
    whenverificationcompleted timestamp with time zone,
    whenverificationstarted timestamp with time zone,
    whenwrappedset timestamp with time zone,
    wrappedsize bigint,
    localregistration_registrationkey integer NOT NULL
    uuid character varying(255),
);

CREATE TABLE poiguid (
    poiguidkey integer PRIMARY KEY,
    value VARCHAR(255)
);

CREATE TABLE poitype (
    poitypekey integer PRIMARY KEY,
    value VARCHAR(255)
);

CREATE TABLE poi(
    pointofinterestkey integer PRIMARY KEY,
    level integer,
    status integer,
    whenoccurred timestamp with time zone,
    guid_poiguidkey integer REFERENCES poiguid (poiguidkey),
    type_poitypekey integer REFERENCES poitype (poitypekey)
);        

CREATE TABLE poiattributename (
    poiattributenamekey integer PRIMARY KEY,
    value character varying(255)
);               

CREATE TABLE poiattribute (
    poiattributekey integer PRIMARY KEY,
    value character varying(255),
    name_poiattributenamekey integer REFERENCES poiattributename (poiattributenamekey),
    pointofinterest_pointofinterestkey integer REFERENCES poi (pointofinterestkey)
); 

--
-- Name: verified; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE verified (
    verifiedkey integer NOT NULL,
    whenabandoned timestamp with time zone,
    whenverified timestamp with time zone,
    destination_neighborkey integer NOT NULL,
    ticketedfile_ticketedfilekey integer NOT NULL
);


--
-- Name: dispatch_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY dispatch
    ADD CONSTRAINT dispatch_pkey PRIMARY KEY (dispatchkey);


--
-- Name: entry_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY entry
    ADD CONSTRAINT entry_pkey PRIMARY KEY (entrykey);


--
-- Name: hierarchicalpath_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY hierarchicalpath
    ADD CONSTRAINT hierarchicalpath_pkey PRIMARY KEY (pathkey);


--
-- Name: inboundregistration_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY inboundregistration
    ADD CONSTRAINT inboundregistration_pkey PRIMARY KEY (registrationkey);


--
-- Name: localregistration_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY localregistration
    ADD CONSTRAINT localregistration_pkey PRIMARY KEY (registrationkey);


--
-- Name: neighbor_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY neighbor
    ADD CONSTRAINT neighbor_pkey PRIMARY KEY (neighborkey);


--
-- Name: slicedexecution_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY slicedexecution
    ADD CONSTRAINT slicedexecution_pkey PRIMARY KEY (slicedexecutionkey);


--
-- Name: slicedinterval_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY slicedinterval
    ADD CONSTRAINT slicedinterval_pkey PRIMARY KEY (intervalkey);


--
-- Name: slicedtopic_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY slicedtopic
    ADD CONSTRAINT slicedtopic_pkey PRIMARY KEY (topickey);


--
-- Name: ticketedfile_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY ticketedfile
    ADD CONSTRAINT ticketedfile_pkey PRIMARY KEY (ticketedfilekey);


--
-- Name: verified_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY verified
    ADD CONSTRAINT verified_pkey PRIMARY KEY (verifiedkey);


--
-- Name: fk14d3a89a3bd268e6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY dispatch
    ADD CONSTRAINT fk14d3a89a3bd268e6 FOREIGN KEY (destination_neighborkey) REFERENCES neighbor(neighborkey);


--
-- Name: fk14d3a89a8f423fff; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY dispatch
    ADD CONSTRAINT fk14d3a89a8f423fff FOREIGN KEY (ticketedfile_ticketedfilekey) REFERENCES ticketedfile(ticketedfilekey);


--
-- Name: fk40018523c5a75fb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entry
    ADD CONSTRAINT fk40018523c5a75fb FOREIGN KEY (datapath_pathkey) REFERENCES hierarchicalpath(pathkey);


--
-- Name: fk4001852561d9a56; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entry
    ADD CONSTRAINT fk4001852561d9a56 FOREIGN KEY (wrappedpath_pathkey) REFERENCES hierarchicalpath(pathkey);


--
-- Name: fk400185291e8ebd6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entry
    ADD CONSTRAINT fk400185291e8ebd6 FOREIGN KEY (metapath_pathkey) REFERENCES hierarchicalpath(pathkey);


--
-- Name: fk4001852c7562352; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entry
    ADD CONSTRAINT fk4001852c7562352 FOREIGN KEY (compressedpath_pathkey) REFERENCES hierarchicalpath(pathkey);


--
-- Name: fk473baf6e283804d6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY hierarchicalpath
    ADD CONSTRAINT fk473baf6e283804d6 FOREIGN KEY (parent_pathkey) REFERENCES hierarchicalpath(pathkey);


--
-- Name: fk8cfa82e83bd268e6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY verified
    ADD CONSTRAINT fk8cfa82e83bd268e6 FOREIGN KEY (destination_neighborkey) REFERENCES neighbor(neighborkey);


--
-- Name: fk8cfa82e88f423fff; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY verified
    ADD CONSTRAINT fk8cfa82e88f423fff FOREIGN KEY (ticketedfile_ticketedfilekey) REFERENCES ticketedfile(ticketedfilekey);


--
-- Name: fk9a7b02061dcae76f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY slicedexecution
    ADD CONSTRAINT fk9a7b02061dcae76f FOREIGN KEY (interval_intervalkey) REFERENCES slicedinterval(intervalkey);


--
-- Name: fk9a7b0206b221dda5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY slicedexecution
    ADD CONSTRAINT fk9a7b0206b221dda5 FOREIGN KEY (topic_topickey) REFERENCES slicedtopic(topickey);


--
-- Name: fkaf3e54e76771ecb7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ticketedfile
    ADD CONSTRAINT fkaf3e54e76771ecb7 FOREIGN KEY (localregistration_registrationkey) REFERENCES localregistration(registrationkey);


--
-- Name: fkce9d32b243c55f52; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY inboundregistration
    ADD CONSTRAINT fkce9d32b243c55f52 FOREIGN KEY (registrationkey) REFERENCES localregistration(registrationkey);


--
-- Name: fkce9d32b2e6dfdf6a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY inboundregistration
    ADD CONSTRAINT fkce9d32b2e6dfdf6a FOREIGN KEY (deliverer_neighborkey) REFERENCES neighbor(neighborkey);


--
-- PostgreSQL database dump complete
--

