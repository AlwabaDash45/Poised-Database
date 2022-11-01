# Created 5 tables with all it's relevant fields
create table Architect(
    ID int primary key,
    Name varchar(50) not null,
    Surname varchar(50) not null,
    TelephoneNumber varchar(50),
    EmailAddress varchar(50),
    PhysicalAddress varchar(50)
);

create table StructuralEngineer(
      ID int primary key,
      Name varchar(50) not null,
      Surname varchar(50) not null,
      TelephoneNumber varchar(50),
      EmailAddress varchar(50),
      PhysicalAddress varchar(50)
);

create table Customer(
    ID int primary key,
    Name varchar(50),
    Surname varchar(50),
    TelephoneNumber varchar(50),
    EmailAddress varchar(50),
    PhysicalAddress varchar(50)
);



create table ProjectManager(
   ID int primary key,
   Name varchar(50) not null,
   Surname varchar(50) not null,
   TelephoneNumber varchar(50),
   EmailAddress varchar(50),
   PhysicalAddress varchar(50)
);

create table PoisedProject(
    ProjectNumber int not null,
    ProjectName varchar(50) not null,
    type_of_building varchar(50),
    PhysicalAddress varchar(50),
    ErfNumber int,
    TotalFee varchar(50),
    TotalAmountPaidDate varchar(50),
    Deadline varchar (50),
    ArchitectID int,
    StructuralEngineerID int,
    CustomerID int,
    ProjectManagerID int,
    Status varchar(50),
    primary key (ProjectNumber, ProjectName),
    foreign key (ArchitectID) references Architect(ID),
    foreign key (StructuralEngineerID) references StructuralEngineer(ID),
    foreign key (CustomerID) references Customer(ID),
    foreign key (ProjectManagerID) references ProjectManager(ID)
);

# # Populating each table with 2 records each
insert into poisepms.architect values(1000, "Ronald", "Koman", "0781234567", "ronaldkoman@gmail.com", "45 Timokil road"),
                                     ( 1001, "Sammeul", "Hector", "0834567822", "samhec@gmail.com", "34 Sam street");

insert into poisepms.structuralengineer values(4000, "Ryan", "Giggs", "06789036196", "ryangig@gmail.com", "23th ryan road"),
                                              (1001, "Frank", "Ink", "0874567804", "frankink@gmail", "16 frank road");

insert into poisepms.customer values(2000, "Jimmy", "Flank", "0764532468", "jimflank@gmail.com", "78 Jim street"),
                                    (2001, "Paul", "Romm", "0671230654", "paulrom@gmail.com", "67 Paul road");

insert into poisepms.projectmanager values(3000, "Stark", "Matt", "0765432178", "starkmatt@gmail.com", "17 stark road"),
                                          (3001, "King", "Killman", "0789543222", "kingkill@gmail.com", "44 King road");

insert into poisepms.poisedproject values(0, "ProjectI", "Mansion", "13 Hill Street", 23, "120000", "40000", "2022-10-17",
                                          1000, 4000, 2000, 3000, "Incomplete"),
                                         (1, "ProjectII", "Apartment", "13 Corner dilver", 9, "87000", "23000", "2022-09-21",
                                          1001, 4000, 2001, 3001, "Incomplete");